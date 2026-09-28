@import hexlet.code.CRUD.dto.UserPage

@param UserPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${page.getUser().getUsername()}</title>
</head>
<body>
<a href="/users">← Все пользователи</a>

<h1>${page.getUser().getUsername()}</h1>
<dl>
    <dt>ID</dt>
    <dd>${page.getUser().getId()}</dd>

    <dt>Username</dt>
    <dd>${page.getUser().getUsername()}</dd>

    <dt>Email</dt>
    <dd>${page.getUser().getEmail()}</dd>
</dl>

<a href="/users/${page.getUser().getId()}/edit">Редактировать</a>
</body>
</html>