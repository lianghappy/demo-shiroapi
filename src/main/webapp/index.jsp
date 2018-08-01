<html>
<body>
<h2>Hello World!</h2>
<button onclick="getToken()">getToken</button><br>
<button onclick="hello()">Hello</button><br>
<script>

    var token="";
    function getToken() {
        $.ajax({
            // url:'http://localhost:8081/login?userName=admin&password=123',
            url:'http://localhost:8081/login?userName=admin&password=123',
            type:'get',
            // beforeSend: function (xhr) {
            //   xhr.setRequestHeader("Access-Token", "b21fd4c6-057a-4953-a584-c882dce08955");
            // },
            success: function(data) {
                console.log(data);
                token = data.token;
            }
        });
    }
    function hello() {
        $.ajax({
            // url:'http://localhost:8081/login?userName=admin&password=123',
            url:'http://localhost:8081/hello',
            type:'get',
            beforeSend:  function(xhr) {
                xhr.setRequestHeader("Access-Token", token);
            },
            success:function (data) {
                console.log(data);
            }
        });
    }

</script>
</body>
</html>
