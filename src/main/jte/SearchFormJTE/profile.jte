@param String email
@param String username


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/form.css">
    <title>Типо сайт</title>
</head>
<body>
    <div class="container-2">
        <div class="profile">
            <div class="profile-image">
                <img src="/assets/images/profile.webp" alt="Ваше фото">
            </div>
            <div class="profile-information">
                <p>Имя: ${username}</p>
                <p>Почта: ${email}</p>
            </div>
            <div class="profile-search-another-profiles">
                <form action="/profile" method="POST">
                    <input type="button" value="искать друзей" class="input submit">
                </form>
            </div>
        </div>
    </div>
</body>
</html>