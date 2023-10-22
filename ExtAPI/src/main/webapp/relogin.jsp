<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <Style>
        h4,h1,form {
                text-align: center;
            }
    </Style>
    
</head>
<body>
    <h1 style="font-family: sans-serif;">Login Page</h1>
    <h4>Wrong login-id/password <br>Re-enter the details</h6>
    <form method="post" action="/ExtAPI/BasicResourceProvider">
        <input type="email" class="form-control" name="email" placeholder="Login id" required><br><br>
        <input type="password" class="form-control" name="password" placeholder="Password" required><br><br>
        <button>Submit</button>
    </form>
</body>
</html>