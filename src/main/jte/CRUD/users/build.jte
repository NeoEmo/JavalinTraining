<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новый пользователь</title>
</head>
<body>
<a href="/users">← Все пользователи</a>
<h1>Новый пользователь</h1>

<form method="POST" action="/users">
    <div>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
    </div>

    <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
    </div>

    <button type="submit">Создать</button>
</form>
</body>
</html>