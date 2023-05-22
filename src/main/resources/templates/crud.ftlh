<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD Cidades</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<div class="container-fluid">
    <div class="jumbotron mt-5">
        <h1>CITIES CRUD</h1>
        <p>A CRUD that can create, update, remove and read</p>
    </div>

    <#if actualCity??>
        <form action="/update" method="POST">
        <input type="hidden" name="actualName" value="${(actualCity.name)!}">
        <input type="hidden" name="actualState" value="${(actualCity.state)!}">
    <#else>
        <form action="/create" method="POST">
    </#if>

        <div class="form-group">
            <label for="name">City:</label>
            <input value="${(actualCity.name)!}" name="name" type="text" class="form-control" placeholder="Enter the city name" id="name">
        </div>
        <div>
            <label for="state">State:</label>
            <input value="${(actualCity.state)!}" name="state" type="text" class="form-control" placeholder="Enter the state name" id="state">
        </div>

        <#if actualCity??>
            <button type="submit" class="btn btn-primary">CONCLUDE UPDATE</button>
        <#else>
            <button type="submit" class="btn btn-primary">CREATE</button>
        </#if>
    </form>

    <table class="table table-striped table-hover mt-5">
        <thead>
        <tr>
            <th>Name</th>
            <th>State</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
            <#list cities as city>
                <tr>
                    <td>${city.name}</td>
                    <td>${city.state}</td>
                    <td>
                        <div class="d-flex d-justicy-content-center">
                            <a href="/prepareUpdate?name=${city.name}&state=${city.state}" class="btn btn-warning mr-3">Update</a>
                            <a href="/delete?name=${city.name}&state=${city.state}" class="btn btn-danger">Delete</a>
                        </div>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

</div>
<body>

</body>
</html>