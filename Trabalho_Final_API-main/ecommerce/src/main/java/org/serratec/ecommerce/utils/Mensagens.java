package org.serratec.ecommerce.utils;

public class Mensagens {

	public static class ApiResponses {
		/* Mensagens genéricas padronizadas */

		public static final String RESPONSE_200 = "Requisiçao realizada com sucesso";
		public static final String ERROR_401 = "Erro de autenticação";
		public static final String ERROR_403 = "Você não tem permissão para acessar o recurso";
		public static final String ERROR_404 = "Recurso não encontrado";
		public static final String ERROR_405 = "Quando ocorre uma exceção";

		public static final String CLIENTE_PESQUISAR_TODOS = "Pesquisar todos os clientes";
	}

	public static class SwaggerMessages {

		/* Mensagens referentes à entidade PEDIDO */
		public static final String IDENTIFICADOR_PEDIDO = "Identificador do pedido";
		public static final String DATA_PEDIDO = "Data do pedido";
		public static final String STATUS_PEDIDO = "Status do pedido";
		public static final String CLIENTE_PEDIDO = "Cliente do pedido";
		public static final String CATEGORIA_PRODUTO = "Categoria do Produto";
		
		/*Mensagens da entidade PedidoItem */
		
		public static final String IDENTIFICADOR_PEDIDO_ITEM = "Identificador do Pedido-item";
		public static final String QUANTIDADE_PRODUTOS_PEDIDO_ITEM = "Quantidade de determinado produto";
		public static final String PESQUISAR_TODOS_PEDIDOS_ITEM = "Retorna todos os PedidoItens";
		public static final String PESQUISAR_PEDIDO_ITEM_ID = "Pesquisa um PedidoItem por ID";
		public static final String INSERIR_PEDIDO_ITEM = "Insere um PedidoItem";
		public static final String EDITAR_PEDIDO_ITEM = "Edita um PedidoItem";
		public static final String REMOVER_PEDIDO_ITEM = "Deleta um PedidoItem";
		public static final String PEDIDO_PEDIDO_ITEM = "Pedido do Pedido Item";
		
		/*Anotações da entidade PedidoItem */
		
		public static final String NOTE_PESQUISAR_TODOS_PEDIDOS_ITEM = "Retorna todos os PedidoItens";
		public static final String NOTE_PESQUISAR_PEDIDO_ITEM_ID = "Pesquisa um PedidoItem por ID";
		public static final String NOTE_INSERIR_PEDIDO_ITEM = "Insere um PedidoItem";
		public static final String NOTE_EDITAR_PEDIDO_ITEM = "Edita um PedidoItem por ID";
		public static final String NOTE_REMOVER_PEDIDO_ITEM = "Deleta um PedidoItem por ID";

		/* Mensagens referentes à entidade CATEGORIA */
		
		public static final String NOME_CATEGORIA = "Nome da categoria";
		public static final String IDENTIFICADOR_CATEGORIA = "Identificador da categoria";		
		public static final String PESQUISAR_TODAS_CATEGORIAS = "Pesquisar todos as categorias";
		public static final String PESQUISAR_CATEGORIA_NOME = "Pesquisar categoria por Nome";
		public static final String PESQUISAR_CATEGORIA_ID = "Pesquisar categoria por ID";
		public static final String INSERIR_CATEGORIA = "Insere uma categoria";
		public static final String EDITAR_CATEGORIA = "Edita uma categoria";
		public static final String REMOVER_CATEGORIA = "Remove uma categoria";	

		/* Anotações da entidade CATEGORIA */
		
		public static final String NOTE_PESQUISAR_TODAS_CATEGORIAS = "Retorna todas as categorias";
		public static final String NOTE_PESQUISAR_CATEGORIA_NOME = "Pesquisar uma categoria por nome";
		public static final String NOTE_PESQUISAR_CATEGORIA_ID = "Pesquisar categoria por ID";
		public static final String NOTE_INSERIR_CATEGORIA = "Insere uma categoria";
		public static final String NOTE_EDITAR_CATEGORIA = "Edita uma categoria através";
		public static final String NOTE_REMOVER_CATEGORIA = "Remove uma categoria através do ID";

		/* Mensagens referentes à entidade PRODUTO */

		public static final String PESQUISAR_TODOS_PRODUTOS = "Pesquisa todos os produtos";
		public static final String PESQUISAR_PRODUTO_NOME = "Pesquisar um produto por nome";
		public static final String CRIAR_PRODUTO = "Cria um produto";
		public static final String ATUALIZAR_PRODUTO = "Atualiza um produto pelo ID";
		public static final String EDITAR_CATEGORIA_PRODUTO = "Edita uma categoria em um produto";
		public static final String DELETAR_PRODUTO = "Deleta um produto por ID";

		/* Anotações da entidade PRODUTO */

		public static final String NOTE_PESQUISAR_TODOS_PRODUTOS = "Pesquisa uma lista com todos os produtos";
		public static final String NOTE_PESQUISAR_PRODUTO_NOME = "Listar produtos pelo nome";
		public static final String NOTE_CRIAR_PRODUTO = "Cria/insere um produto";
		public static final String NOTE_ATUALIZAR_PRODUTO = "Atualiza um produto pelo ID";
		public static final String NOTE_EDITAR_CATEGORIA_PRODUTO = "Edita/Insere uma categoria em um produto já existente";
		public static final String NOTE_DELETAR_PRODUTO = "Deleta um produto por ID";
		
	}

	public static class NotNullBlank {

		public static final String DATA_NOTNULL = "O campo 'Data' não pode estar vazio.";
		public static final String NOME_NOTNULL = "O campo 'Nome' não pode estar vazio.";
		public static final String SOBRENOME_NOTNULL = "O campo 'Sobrenome' não pode estar vazio.";
		public static final String EMAIL_NOTNULL = "O campo 'E-mail' não pode estar vazio.";
		public static final String QUANTIDADE_NOTNULL = "O campo 'Quantidade' não pode estar vazio.";
		public static final String DESCRICAO_NOTNULL = "O campo 'Descrição' não pode estar vazio.";
		public static final String STATUS_NOTNULL = "O campo 'Status' não pode estar vazio.";
		public static final String CLIENTE_NOTNULL = "O campo 'Cliente' não pode estar vazio.";
		public static final String VALOR_NOTNULL = "O campo 'Valor' não pode estar vazio.";
		public static final String VALOR_TOTAL_PEDIDOITEM_NOTNULL = "O campo 'Valor' não pode estar vazio.";
		public static final String ENDERECO_NOTNULL = "O campo 'Endereço' não pode estar vazio.";
		public static final String CEP_NOTNULL = "O campo 'CEP' não pode estar vazio.";
		public static final String COMPLEMENTO_NOTNULL = "O campo 'Complemento' não pode estar vazio.";
		public static final String PEDIDO_NOTNULL = "O pedido não pode estar vazio.";
		public static final String ID_PEDIDO_NOTNULL = "O campo 'Produto ID' não pode estar vazio.";
	}

	public static class Exceptions {

		public static final String PESQUISA_FALHOU = "Não foi possível localizar o item: ";
		public static final String PESQUISA_PEDIDO_FALHOU = "Não foi possível localizar o Pedido: ";
		public static final String PESQUISA_PRODUTO_FALHOU = "Não foi possível localizar o Produto: ";
		public static final String PESQUISA_CLIENTE_FALHOU = "Não foi possível localizar o Cliente: ";
		public static final String PESQUISA_CATEGORIA_FALHOU = "Não foi possível localizar a Categoria: ";
		public static final String PESQUISA_ENDERECO_FALHOU = "Não foi possível localizar o Endereço: ";
		public static final String PESQUISA_CEP_FALHOU = "Não foi possível localizar o CEP: ";

		public static final String INSERCAO_FALHOU = "Não foi possível adicionar.";
		public static final String INSERCAO_PEDIDO_FALHOU = "Não foi possível adicionar o Pedido: ";
		public static final String INSERCAO_CLIENTE_FALHOU = "Não foi possível adicionar o Cliente: ";
		public static final String INSERCAO_CATEGORIA_FALHOU = "Não foi possível adicionar a Categoria: ";
		public static final String INSERCAO_CATEGORIA_EXISTENTE_FALHOU = "Não foi possível adicionar a Categoria, pois já existe: ";
		public static final String INSERCAO_PRODUTO_EXISTENTE_FALHOU = "Já foi inserido este Produto ID: ";
		public static final String INSERCAO_ENDERECO_FALHOU = "Não foi possível adicionar o Endereço: ";
		public static final String INSERCAO_EMAIL_EXISTENTE_FALHOU = "Não foi possível adicionar o Cliente, pois o e-mail já existe: ";

		public static final String EDICAO_FALHOU = "Não foi possível editar.";
		public static final String EDICAO_PEDIDO_FALHOU = "Não foi possível editar o Pedido: ";
		public static final String EDICAO_PRODUTO_FALHOU = "Não foi possível editar o Produto: ";
		public static final String EDICAO_CLIENTE_FALHOU = "Não foi possível editar o Cliente: ";
		public static final String EDICAO_CATEGORIA_FALHOU = "Não foi possível editar a Categoria: ";
		public static final String EDICAO_ENDERECO_FALHOU = "Não foi possível editar o Endereço: ";

		public static final String EXCLUSAO_FALHOU = "Não foi possível excluir.";
		public static final String EXCLUSAO_PEDIDO_FALHOU = "Não foi possível excluir o Pedido: ";
		public static final String EXCLUSAO_PRODUTO_FALHOU = "Não foi possível excluir o Pedido: ";
		public static final String EXCLUSAO_CLIENTE_FALHOU = "Não foi possível excluir o Cliente: ";
		public static final String EXCLUSAO_CATEGORIA_FALHOU = "Não foi possível excluir a Categoria: ";

		public static final String SALVAR_FALHOU = "Não foi possível salvar! Erro: ";
		public static final String ITEM_DUPLICADO_FALHOU = "Por favor, verifique se o mesmo Item não foi inserido duas vezes.";

		public static final String CAMPOS_INVALIDOS = "Existem campos inválidos. Confira o preenchimento.";
		public static final String PEDIDO_STATUS_FAIL = "Não é possível alterar um pedido com status: ";

		public static final String ENVIO_EMAIL_FALHOU = "Não foi possível enviar o e-mail.";
		public static final String ERRO_EMAIL = "Erro ao enviar e-mail: ";

		public static final String ESTOQUE_INFERIOR = "Estoque inferior ao solicitado. Quantidade em estoque: ";

		public static final String DATA_ANTERIOR = "A data deve ser anterior à data atual.";
		public static final String INTEGRIDADE_FAIL = "Não é possível excluir/editar este campo, pois ele está relacionado à outra informação na base de dados.";
		public static final String EXCLUSAO_EDICAO_FAIL = "Exclusão/Edição falou.";

		/* Mensagem de erro StatusPedido */

		public static final String PREENCHIMENTO_INCORRETO = "Status preenchido incorretmente. ";
	}
}
