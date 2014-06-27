var stompClient = null;

function setConnected(connected) {
	document.getElementById('connect').disabled = connected;
	document.getElementById('disconnect').disabled = !connected;
	document.getElementById('conversationDiv').style.visibility = connected ? 'visible'
			: 'hidden';
	document.getElementById('response').innerHTML = '';
}

function connect() {
	var socket = new SockJS('/hello');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		var user = frame.headers['user-name'];
		stompClient.subscribe('/topic/greetings', function(greeting) {
			showGreeting(JSON.parse(greeting.body).content);
		});
		stompClient.subscribe("/user/queue/errors", function(msg) {
			$.gritter.add({
				title: 'An error occured',
				text: msg.body,
				class_name: 'gritter-error gritter-light'
			});
		  });
	});
}

function disconnect() {
	stompClient.disconnect();
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	var name = document.getElementById('name').value;
	stompClient.send("/app/hello", {}, JSON.stringify({
		'name' : name
	}));
}

function showGreeting(message) {
	var response = document.getElementById('response');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.appendChild(document.createTextNode(message));
	response.appendChild(p);
}
