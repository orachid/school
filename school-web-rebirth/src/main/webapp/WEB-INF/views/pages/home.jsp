<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset=UTF-8>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
</head>
<body>
<table class="table" border="1" bordercolor="grey" style="margin: 30px auto 0 auto;width: 500px;">
    <thead>
    <tr>
        <th>
            <lablel>Book name</lablel>
        </th>
        <th>
            <lablel>Price</lablel>
        </th>
        <th>
            <lablel>Author</lablel>
        </th>
    </tr>
    </thead>
    <tbody>
    {{#bookList}}
    <tr>
        <td>
            {{name}}
        </td>
        <td>
            {{price}}
        </td>
        <td>
            {{author}}
        </td>
    </tr>
    {{/bookList}}
    </tbody>
</table>
</body>
</html>