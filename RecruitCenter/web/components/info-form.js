const { useState } = React;

function ValidFeedback() {
    return React.createElement('small', { id: 'email-feedback', className: 'valid-feedback' }, 'Look good!');
}

function InvalidFeedback({ labelValue, requirement }) {
    return React.createElement('small', { id: 'email-feedback', className: 'invalid-feedback' },
        'Please select a valid ' + labelValue + '!' + ' Try: ' + requirement);
}

function EmailInput() {
    // const [email, setEmail] = useState('');

    const validateEmail = (value) => {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(value);
    };


    return React.createElement('div', {
        className: 'form-group',
        id: 'email'
    },
        React.createElement('label', { htmlFor: 'email' }, 'Email'),
        React.createElement('input', {
            type: 'email',
            className: 'form-control',
            onChange: function (e) {
                const input = e.target;
                // setEmail(e.target.value);
                if (input.value == '') {
                    e.target.classList.remove('is-valid');
                    e.target.classList.remove('is-invalid');
                }
                else if (validateEmail(input.value)) {
                    e.target.classList.remove('is-invalid');
                    e.target.classList.add('is-valid');
                } else {
                    e.target.classList.remove('is-valid');
                    e.target.classList.add('is-invalid');
                }
            },
        }),
        React.createElement(ValidFeedback, null, null),
        React.createElement(InvalidFeedback, { labelValue: 'email', requirement: 'minimum 1 character before and after the @, and an domain after dot.' }, null)
    )
}

function PasswordInput() {
    // const [pw, setPw] = useState('');

    const validatePw = (value) => {
        const pwPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
        return pwPattern.test(value);
    };

    return React.createElement('div', {
        className: 'form-group',
    },
        React.createElement('label', { htmlFor: 'pw' }, 'Password'),

        React.createElement('input', {
            id: 'pw',
            type: 'password',
            name: 'password',
            className: 'form-control',
            onChange: function (e) {
                const input = e.target;
                // setPw(input.value);
                if (input.value == '') {
                    e.target.classList.remove('is-valid');
                    e.target.classList.remove('is-invalid');
                }
                else if (validatePw(input.value)) {
                    e.target.classList.remove('is-invalid');
                    e.target.classList.add('is-valid');
                } else {
                    e.target.classList.remove('is-valid');
                    e.target.classList.add('is-invalid');
                }
            },
        }),
        React.createElement(ValidFeedback, null, null),
        React.createElement(InvalidFeedback, { labelValue: 'password', requirement: 'length from 6 to 20, must contain atleaset 1 uppercase, 1 lowercase, 1 digit.' }, null)
    )
}

function LoginButton() {
    return React.createElement('input',
        {
            type: 'submit',
            className: 'btn-primary',
            value: 'Continue'
        }
    )
}

function LoginForm() {
    return React.createElement('form',
        {
            method: 'POST',
            action: '', //servlet
            className: '',
            onSubmit: function (e) {
                e.preventDefault();
                const inputs = document.getElementsByTagName("input");
                let isValid = true;

                for (var i = 0; i < inputs.length; i++) {
                    if (inputs[i].classList.contains("is-invalid")) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    e.target.submit();
                } 
            },
        },
        React.createElement(EmailInput),
        React.createElement(PasswordInput),
        React.createElement(LoginButton),
    )
}

ReactDOM.render(React.createElement(LoginForm), document.getElementById('login-form'));
