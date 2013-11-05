<html>
<head>
    <style type="text/css">
        body {
            height: 100%;
        }

        body > table {
            width: 100%;
            height: 100%;
        }

        body > table > tbody > tr > td
        {
            text-align: center;
        }

        form > table
        {
            margin-left:auto;
            margin-right:auto;
        }

        .error
        {
            font-weight: bold;
            color: red;
        }
        
        div .login-panel {
        	-webkit-animation: move-up-100 800ms cubic-bezier(0.165, 0.840, 0.440, 1.000);
			-moz-animation: move-up-100 800ms cubic-bezier(0.165, 0.840, 0.440, 1.000);
			-o-animation: move-up-100 800ms cubic-bezier(0.165, 0.840, 0.440, 1.000);
		}
        
        .login-panel h4 {
			font-weight: bold;
			text-transform: uppercase;
			color: #349aff;
		}
		
		.login-panel {
			background: #fff;
			
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
			
			-webkit-box-shadow: 0 4px 20px rgba(0,0,0,.5);
			-moz-box-shadow: 0 4px 20px rgba(0,0,0,.5);
			box-shadow: 0 4px 20px rgba(0,0,0,.5);
			
			padding: 1em 1em 2em;
			
			margin-left: auto;
			margin-right: auto;
			
			vertical-align: middle;
			display: inline-block;
			-webkit-animation: move-up-100 800ms cubic-bezier(0.165, 0.84, 0.44, 1);
		}
		.login-panel input{
			width: 150px;
			-webkit-border-radius: 3px;
			-moz-border-radius: 3px;
			border-radius: 3px;
			border: 1px solid #c9c8c7;
			border-top-color: #d8d7d6;
			background: #fff;
			box-shadow: 0 0 0 2px rgba(51, 50, 49, 0.07);
			font: inherit;
			color: inherit;
			line-height: 1;
			margin: 0;
		}
		
		.login-panel input:focus{
			outline: none;
			border-color: #69a5e4;
		}
		
		.login-panel .button{
			color: #fff;
			text-shadow: 0 -1px 0 rgba(0,0,0,.4);
			font-weight: bold;
			-webkit-font-smoothing: antialiased;
			
			border-color: #216ec6;
			border-top-color: #2875ce;
			border-bottom-color: #37679d;
			
			background: #499bea;
			
			background: -moz-linear-gradient(top, #4fa8f7 0%, #499bea 6%, #207ce5 100%);
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#4fa8f7), color-stop(6%,#499bea), color-stop(100%,#207ce5));
			background: -webkit-linear-gradient(top, #4fa8f7 0%,#499bea 6%,#207ce5 100%);
			background: -o-linear-gradient(top, #4fa8f7 0%,#499bea 6%,#207ce5 100%);
			background: -ms-linear-gradient(top, #4fa8f7 0%,#499bea 6%,#207ce5 100%);
			/* background: linear-gradient(to bottom, #4fa8f7 0%,#499bea 6%,#207ce5 100%); */
		}
		.button:focus {
			xborder-color: #207ce5;
			webkit-box-shadow: 0 0 4px #207ce5;
			-moz-box-shadow: 0 0 4px #207ce5;
			box-shadow: 0 0 4px #207ce5;
		}
		
		
		.button:active{
			border-color: #1c64b1;
			border-top-color: #346d9f;
			border-bottom-color: #3c81be;
			background: #3d80bf;
			background: -moz-linear-gradient(top, #3d80bf 0%, #408ac9 6%, #1a6abf 100%);
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#3d80bf), color-stop(8%,#408ac9), color-stop(100%,#1a6abf));
			background: -webkit-linear-gradient(top, #3d80bf 0%,#408ac9 6%,#1a6abf 100%);
			background: -o-linear-gradient(top, #3d80bf 0%,#408ac9 6%,#1a6abf 100%);
			background: -ms-linear-gradient(top, #3d80bf 0%,#408ac9 6%,#1a6abf 100%);
			/* background: linear-gradient(to bottom, #3d80bf 0%,#408ac9 6%,#1a6abf 100%); */
		
			-webkit-box-shadow: 0 0 0 2px rgba(49,50,51,.07);
			-moz-box-shadow: 0 0 0 2px rgba(49,50,51,.07);
			box-shadow: 0 0 0 2px rgba(49,50,51,.07);
		}
    </style>
</head>
<body>

        	<div class="login-panel">
            	<h4>School Web Application</h4>
	            <form method="post" action="/j_spring_security_check">
	                <table>
	                    <tr>
	                        <td>Username</td>
	                        <td>Password</td>
	                        <td>&nbsp;</td>
	                    </tr>
	                    <tr>
	                        <td><input type="text" name="j_username"/></td>
	                        <td><input type="password" name="j_password"/></td>
	                        <td><input type="submit" value="Login" class="button"></td>
	                    </tr>
	                </table>
	            </form>

            	<#if isError?? && isError?string == "true">
                	<div class="error">User not found</div>
            	</#if>
			</div>
</body>
</html>