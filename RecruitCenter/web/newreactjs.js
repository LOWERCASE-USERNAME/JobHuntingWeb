//import React from 'react';
//import ReactDOM from 'react-dom';

function App() {
  return React.createElement('div', null,
    React.createElement('h1', null, 'Hello, World!'),
    React.createElement('p', null, 'This is my React app.')
  );
}

function UsernameInput(){
  return React.createElement('')
}

ReactDOM.render(React.createElement(App), document.getElementById('root'));





