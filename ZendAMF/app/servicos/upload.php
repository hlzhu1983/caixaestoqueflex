<?php

    if (isset($_FILES['imagem']['name']) && strlen($_FILES['imagem']['name']) > 1){
        $dir="./img/";
       
		$nomeImagem = $_REQUEST['nome'];
		$caminho= $dir.$nomeImagem;
		echo "entrou<br />";
		$origem  = $_FILES['imagem']['tmp_name'];
		
		echo $caminho;
		
	if(!move_uploaded_file($origem, $caminho)){
			echo "não fez";
		}else
		{
			echo "fez";
		}
		
		
	}

?>