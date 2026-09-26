@import java.util.List
@import hexlet.code.searchFormJTE.model.Error

@param List<Error> errors
@param String username
@param String email

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/form.css">
    <title>Регистрация</title>
</head>
<body>
    <div class="container">
        <div class="registration">
            <form method="POST" action="/registration">
                <input type="text" required name="username" placeholder="Username" class="input">
                @for(Error e : errors)
                    @if(e.getField().equals("username"))
                        <span class="error">${e.getError()}</span>
                    @endif
                @endfor
                <input type="password" required name="password" placeholder="Password" class="input">
                @for(Error e : errors)
                    @if(e.getField().equals("password"))
                        <span class="error">${e.getError()}</span>
                    @endif
                @endfor
                <input type="email" required name="email" placeholder="Email" class="input">
                @for(Error e : errors)
                    @if(e.getField().equals("email"))
                        <span class="error">${e.getError()}</span>
                    @endif
                @endfor
                <input type="submit" value="Зарегистрироваться" class="input submit">
            </form>
        </div>
    </div>
</body>
</html>