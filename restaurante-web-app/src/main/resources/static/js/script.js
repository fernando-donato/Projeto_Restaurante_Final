// Aguarda o DOM carregar completamente
document.addEventListener('DOMContentLoaded', () => {

  // --- ARMAZENAMENTO EM MEMÓRIA (FRONT-END) ---
  let allEmployees = [];
  let allMenuItems = [];
  let cart = [];

  // --- ELEMENTOS DO DOM ---
  const screens = document.querySelectorAll('.screen');
  const loginForm = document.getElementById('login-form');
  const logoutBtn = document.getElementById('logout-btn');
  const menuButtons = document.querySelectorAll('.menu-btn');
  const backButtons = document.querySelectorAll('.back-btn');

  // Formulários
  const employeeForm = document.getElementById('employee-form');
  const customerForm = document.getElementById('customer-form');
  const orderForm = document.getElementById('order-form');

  // Elementos da Tela de Pedidos
  const waiterSelect = document.getElementById('order-waiter');
  const orderDatetime = document.getElementById('order-datetime');
  const menuItemsContainer = document.getElementById('menu-items');
  const cartBody = document.getElementById('cart-body');
  const cartTotalInput = document.getElementById('cart-total');

  // Elementos do Pop-up
  const popupOverlay = document.getElementById('popup-overlay');
  const popupMessage = document.getElementById('popup-message');
  const popupCloseBtn = document.getElementById('popup-close');
  const popupOkBtn = document.getElementById('popup-ok-btn');


  // --- FUNÇÕES DE API (NOVAS) ---

  /**
   * Função genérica para chamadas fetch
   * @param {string} url - A URL da API
   * @param {object} options - Opções do fetch (method, headers, body)
   */
  async function apiCall(url, options = {}) {
      try {
          const response = await fetch(url, options);

          if (!response.ok) {
              let errorMessage = `Erro ${response.status}: ${response.statusText}`;
              try {
                  const errorText = await response.text();
                  errorMessage = errorText || errorMessage;
              } catch (e) {
                  // Ignora
              }
              throw new Error(errorMessage);
          }

          if (response.status === 204) {
              return null;
          }

          if (response.headers.get("Content-Type")?.includes("application/json")) {
               return await response.json();
          }

          return await response.text();

      } catch (error) {
          console.error(`Falha na chamada API para ${url}:`, error);
          showPopup(`Erro de comunicação com o servidor: ${error.message}`);
          throw error;
      }
  }

  /**
   * Carrega os garçons da API e atualiza o <select>
   */
  async function loadWaiters() {
      try {
          const waiters = await apiCall('/api/funcionarios/garcons');
          allEmployees = waiters;
          updateWaiterList();
      } catch (error) {
          waiterSelect.innerHTML = '<option value="">Erro ao carregar garçons</option>';
      }
  }

  /**
   * Carrega o cardápio da API
   */
  async function loadMenu() {
      try {
          const menu = await apiCall('/api/pratos');
          allMenuItems = menu;
          populateMenu();
      } catch (error) {
          menuItemsContainer.innerHTML = '<p>Erro ao carregar o cardápio.</p>';
      }
  }


  // --- FUNÇÕES PRINCIPAIS (MODIFICADAS) ---

  function showScreen(screenId) {
      screens.forEach(screen => {
          screen.classList.toggle('active', screen.id === screenId);
      });

      if (screenId === 'order-screen') {
          updateOrderScreenData();
          loadWaiters();
          loadMenu();
      }
      if (screenId === 'login-screen') {
          resetCart();
          orderForm.reset();
      }
  }

  function showPopup(message) {
      popupMessage.textContent = message;
      popupOverlay.style.display = 'flex';
  }

  function closePopup() {
      popupOverlay.style.display = 'none';
  }

  function updateWaiterList() {
      waiterSelect.innerHTML = '<option value="">Selecione o garçom</option>';
      allEmployees.forEach(waiter => {
          const option = document.createElement('option');
          option.value = waiter.id;
          option.textContent = waiter.name;
          waiterSelect.appendChild(option);
      });
  }

  function populateMenu() {
      menuItemsContainer.innerHTML = '';
      allMenuItems.forEach(item => {
          const itemElement = document.createElement('div');
          itemElement.classList.add('menu-item');
          itemElement.innerHTML = `
              <label for="item-${item.id}">${item.nome} (R$ ${item.preco.toFixed(2)})</label>
              <input 
                  type="number" 
                  id="item-${item.id}" 
                  class="menu-item-qty" 
                  min="0" 
                  value="0"
                  data-id="${item.id}"
                  data-price="${item.preco}"
                  data-name="${item.nome}"
              >
          `;
          menuItemsContainer.appendChild(itemElement);
      });

      document.querySelectorAll('.menu-item-qty').forEach(input => {
          input.addEventListener('input', updateCart);
      });
  }

  function updateOrderScreenData() {
      const now = new Date();
      orderDatetime.textContent = now.toLocaleString('pt-BR');
  }

  function updateCart() {
      cart = [];
      cartBody.innerHTML = '';
      let total = 0;
      const qtyInputs = document.querySelectorAll('.menu-item-qty');

      qtyInputs.forEach(input => {
          const qty = parseInt(input.value);
          if (qty > 0) {
              const id = parseInt(input.dataset.id);
              const name = input.dataset.name;
              const price = parseFloat(input.dataset.price);
              const itemTotal = qty * price;
              total += itemTotal;

              cart.push({ id, qty }); 

              const row = cartBody.insertRow();
              row.innerHTML = `
                  <td>${name}</td>
                  <td class="text-right">${qty}</td>
                  <td class="text-right">R$ ${itemTotal.toFixed(2)}</td>
              `;
          }
      });

      cartTotalInput.value = `R$ ${total.toFixed(2)}`;
  }

  function resetCart() {
      cart = [];
      cartBody.innerHTML = '';
      cartTotalInput.value = 'R$ 0.00';
      document.querySelectorAll('.menu-item-qty').forEach(input => {
          input.value = 0;
      });
  }


  // --- NAVEGAÇÃO E EVENT LISTENERS (MODIFICADOS) ---

  loginForm.addEventListener('submit', async (e) => {
      e.preventDefault();

      const loginRequest = {
          username: document.getElementById('username').value,
          password: document.getElementById('password').value
      };

      try {
          const responseText = await apiCall('/api/login', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(loginRequest)
          });

          console.log('Login OK:', responseText);
          showScreen('menu-screen');

      } catch (error) {
          console.error("Falha no login:", error.message);
      }
  });

  logoutBtn.addEventListener('click', () => {
      showScreen('login-screen');
  });

  menuButtons.forEach(button => {
      button.addEventListener('click', () => {
          const targetScreen = button.dataset.target;
          showScreen(targetScreen);
      });
  });

  backButtons.forEach(button => {
      button.addEventListener('click', () => {
          const targetScreen = button.dataset.target;
          showScreen(targetScreen);
      });
  });

  employeeForm.addEventListener('submit', async (e) => {
      e.preventDefault();

      const novoFuncionario = {
          nome: document.getElementById('emp-name').value,
          cargo: document.getElementById('emp-role').value,
          horarioTrabalho: document.getElementById('emp-shift').value,
          dataAdmissao: document.getElementById('emp-admission-date').value,
          cpf: document.getElementById('emp-cpf').value || null,
          salario: parseFloat(document.getElementById('emp-salario').value) || null
      };

      try {
          const funcionarioSalvo = await apiCall('/api/funcionarios', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(novoFuncionario)
          });

          console.log('Funcionário salvo:', funcionarioSalvo);
          showPopup('Funcionário cadastrado com sucesso!');
          employeeForm.reset();
          showScreen('menu-screen');

      } catch (error) {
          // apiCall já mostrou o erro
      }
  });

  customerForm.addEventListener('submit', async (e) => {
      e.preventDefault();

      const novoCliente = {
          nome: document.getElementById('cust-name').value,
          telefone: document.getElementById('cust-phone').value,
          email: document.getElementById('cust-email').value
      };

      try {
          const clienteSalvo = await apiCall('/api/clientes', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(novoCliente)
          });

          console.log('Cliente salvo:', clienteSalvo);
          showPopup('Cliente cadastrado com sucesso!');
          customerForm.reset();
          showScreen('menu-screen');

      } catch (error) {
           // apiCall já mostrou o erro
      }
  });

  orderForm.addEventListener('submit', async (e) => {
      e.preventDefault();

      if (cart.length === 0) {
          showPopup('Por favor, adicione itens ao pedido.');
          return;
      }

      const waiterId = parseInt(waiterSelect.value);
      if (!waiterId) {
           showPopup('Por favor, selecione um garçom.');
          return;
      }

      const pedidoDTO = {
          idFuncionario: waiterId,
          itens: cart // O carrinho já está no formato [{ id, qty }, ...]
      };

      try {
          const pedidoSalvo = await apiCall('/api/pedidos', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(pedidoDTO)
          });

          console.log('Pedido registrado:', pedidoSalvo);
          showPopup('Pedido registrado com sucesso!');
          resetCart();
          orderForm.reset();
          showScreen('menu-screen');

      } catch (error) {
          // apiCall já mostrou o erro
      }
  });

  // Listeners para fechar o Pop-up
  popupCloseBtn.addEventListener('click', closePopup);
  popupOkBtn.addEventListener('click', closePopup);
  popupOverlay.addEventListener('click', (e) => {
      if (e.target === popupOverlay) {
          closePopup();
      }
  });

  // --- INICIALIZAÇÃO ---
  showScreen('login-screen');
});
