<!DOCTYPE html>
<html lang="pt-br">
<?php
session_start();
if ((!isset($_SESSION['userName']) == true) and (!isset($_SESSION['isAdm']) == true)) {
    unset($_SESSION['userName']);
    unset($_SESSION['isAdm']);
    header('Location: ../../home/php');
} else {
    if ($_SESSION['isAdm'] == 0) {
        header('Location: ../../listaProdutos/php');
    }
}
?>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../utils//globalCss.css">
    <link rel="stylesheet" href="../css/listaDeUsuarios.css">
    <link rel="stylesheet" href="../../utils//global_css/links.css">
    <link rel="stylesheet" href="../../utils//global_css//buttons.css">
    <title>Programação Web - PUC-Pr</title>
</head>

<body>
    <header class="display_flex_col">
        <div class="display_flex_row container_list bem_vindo">
            <h1>Lista de Usuários</h1>
            <?php
            echo "<p>Bem-vindo(a) administrador(a) " . $_SESSION['userName'] . " </p> ";
            ?>
        </div>
        <nav class="container_list">
            <ul class="box_list">
                <li><a href="../../minhaLista/php/">Minha Lista</a></li>
                <li><a href="../../cadastroDeProdutos/php/">Cadastro de Produtos</a> </li>
                <li><a href="../../cadastroUsuario/php/">Cadastro de Usuários</a></li>
                <li><a href="../../meusDados/php/">Meus Dados</a></li>
                <li><a href="../../listaProdutos/php/">Lista de Produtos</a></li>
                <form action="../../utils//logout.php" method="get">
                    <button type="submit" class="logout_btn">Sair</button>
                </form>
            </ul>
        </nav>
    </header>
    <main class="display_flex_col">
        <section class="display_flex_col">
            <article class="display_flex_col container_list_products">
                <table>
                    <thead>
                        <tr>
                            <th>Usuário</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        require('./getUsers.php');
                        getUsers();
                        ?>
                    </tbody>
                </table>
            </article>
        </section>
    </main>

</body>

</html>