<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <style>
        .btn{
            margin-left: 22%;
        }
    </style>
</head>
<body>
        <h1 style="font-family: sans-serif;">Customer Details</h1>
        <form method="post" action="/ExtAPI/Create">
            <table>
                <tr>
                    <td><input type="text" class="form-control" name="first_name" placeholder="First Name" required><br></td>
                    <td><input type="text" class="form-control" name="last_name" placeholder="Last Name" required><br></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="street" placeholder="Street" required><br></td>
                    <td><input type="text" class="form-control" name="address" placeholder="Address" required><br></td>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" name="city" placeholder="City" required><br></td>
                    <td><input type="text" class="form-control" name="state" placeholder="State" required><br></td>
                </tr>
                    <td><input type="email" class="form-control" name="email" placeholder="Email" required><br></td>
                    <td><input type="text" class="form-control" name="phone" placeholder="Phone" required><br></td>
                </tr>
            </table>
            <button class="btn">Submit</button>
        </form>
</body>
</html>