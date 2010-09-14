package services;

import java.sql.SQLException;

public aspect ServicesHandler {

	String str[] = { "Erro ao salvar item!", "Erro ao remover item!",
			"Erro ao atualizar item!", "Erro ao litar itens!",
			"Erro ao pesquisar item!", "Erro ao verificar se o item existe!",

	};

	private pointcut salvarItemHandler(): execution(* services.Servicos*.add*(..));

	private pointcut removerItemHandler(): execution(* services.Servicos*.remover*(..));

	private pointcut atualizarItemHandler(): execution(* services.Servicos*.atualizar*(..));

	private pointcut listaItemsHandler(): execution(* services.Servicos*.get*(..));

	private pointcut procuarItensHandler(): execution(* services.Servicos*.pesquisar*(..));

	private pointcut existeItemHandler(): execution(* services.Servicos*.isExiste*(..));

	private pointcut abrirHandler(): execution(* services.Servicos*.abrir*(..));

	private pointcut fecharHandler(): execution(* services.Servicos*.fechar*(..));

	private pointcut cancelarHandler(): execution(* services.Servicos*.cancelar*(..));

	private pointcut filtrarHandler(): execution(* services.Servicos*.filtrar*(..));

	//private pointcut toHandler(): execution(* services.Servicos*.to*(..));

	declare soft: SQLException: salvarItemHandler() || removerItemHandler() || atualizarItemHandler() || listaItemsHandler() || procuarItensHandler() || existeItemHandler() || abrirHandler() || fecharHandler() || cancelarHandler() || filtrarHandler();

	declare parents : services.Servicos* extends services.Servico;

	Object around(Servico svc): (salvarItemHandler() || removerItemHandler() || atualizarItemHandler() || listaItemsHandler() || procuarItensHandler() || existeItemHandler() || abrirHandler() || fecharHandler() || cancelarHandler() || filtrarHandler()) && target(svc){
		try {
			return proceed(svc);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (!svc.banco.getConexao().getAutoCommit())
					svc.banco.getConexao().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(thisEnclosingJoinPointStaticPart.getId());
			System.out.println(thisJoinPoint.getSignature());
			throw new RuntimeException(e.getMessage()
					+ str[thisJoinPoint.getStaticPart().getId()]);

		} finally {
			try {
				svc.getBanco().close();
			} catch (SQLException e) {
			}
		}

	}

}
