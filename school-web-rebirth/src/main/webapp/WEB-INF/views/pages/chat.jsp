<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<noscript>
	<h2 style="color: #ff0000">Seems your browser doesn't support
		Javascript! Websocket relies on Javascript being enabled. Please
		enable Javascript and reload this page!</h2>
</noscript>
<div>
	<div>
		<button id="connect" onclick="connect();">Connect</button>
		<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
	</div>
	<div id="conversationDiv">
		<label>What is your name?</label><input type="text" id="name" />
		<button id="sendName" onclick="sendName();">Send</button>
		<p id="response"></p>
	</div>
</div>
<!-- #section:pages/dashboard.conversations -->
<div class="dialogs">
	{{#page.conversations}}
	<div class="itemdiv dialogdiv">
		<div class="user">
			<img alt="{{name}}'s Avatar" src="{{path.assets}}/avatars/{{avatar}}" />
		</div>

		<div class="body">
			<div class="time">
				<i class="ace-icon fa fa-clock-o"></i> <span class="{{time-color}}">{{time}}</span>
			</div>
			<div class="name">
				<a href="#">{{name}}</a> {{#admin}} <span
					class="label label-info arrowed arrowed-in-right">admin</span>
				{{/admin}}
			</div>
			<div class="text">{{message}}</div>

			<div class="tools">
				<a href="#" class="btn btn-minier btn-info"><i
					class="icon-only ace-icon fa fa-share"></i></a>
			</div>
		</div>
	</div>
	<div class="itemdiv dialogdiv">
		<div class="user">
			<img alt="Alexa's Avatar" src="../resources/avatars/avatar1.png" />
		</div>

		<div class="body">
			<div class="time">
				<i class="ace-icon fa fa-clock-o"></i> <span class="green">4
					sec</span>
			</div>

			<div class="name">
				<a href="#">Alexa</a>
			</div>
			<div class="text">Lorem ipsum dolor sit amet, consectetur
				adipiscing elit. Quisque commodo massa sed ipsum porttitor
				facilisis.</div>

			<div class="tools">
				<a href="#" class="btn btn-minier btn-info"> <i
					class="icon-only ace-icon fa fa-share"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="itemdiv dialogdiv">
		<div class="user">
			<img alt="Alexa's Avatar" src="../resources/avatars/avatar1.png" />
		</div>

		<div class="body">
			<div class="time">
				<i class="ace-icon fa fa-clock-o"></i> <span class="green">4
					sec</span>
			</div>

			<div class="name">
				<a href="#">Alexa</a>
			</div>
			<div class="text">Lorem ipsum dolor sit amet, consectetur
				adipiscing elit. Quisque commodo massa sed ipsum porttitor
				facilisis.</div>

			<div class="tools">
				<a href="#" class="btn btn-minier btn-info"> <i
					class="icon-only ace-icon fa fa-share"></i>
				</a>
			</div>
		</div>
	</div>
	{{/page.conversations}}
</div>
<!-- /section:pages/dashboard.conversations -->

<form>
	<div class="form-actions">
		<div class="input-group">
			<input placeholder="Type your message here ..." type="text"
				class="form-control" name="message" /> <span
				class="input-group-btn">
				<button class="btn btn-sm btn-info no-radius" type="button">
					<i class="ace-icon fa fa-share"></i> Send
				</button>
			</span>
		</div>
	</div>
</form>