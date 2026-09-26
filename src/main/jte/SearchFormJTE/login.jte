@param String error

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/form.css">
    <title>Войти</title>
</head>
<body>
<div class="container">
    <div class="registration">
        <form method="POST" action="/login">
            <input type="text" required name="username" placeholder="Username" class="input">
            <input type="password" required name="password" placeholder="Password" class="input">
            @if(error.equals("Invalid username or password"))
                <span class="error">${error}</span>
            @endif
            <input type="submit" value="Войти" class="input submit">
        </form>
    </div>
</div>
</body>
</html>