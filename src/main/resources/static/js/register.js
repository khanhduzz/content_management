let isEmailValid;
let isPasswordValid;
let isRePasswordValid;
let isValidInputUsername;

// USERNAME
function validateUsername(e) {
    const value = e.target.value;
    isValidInputUsername = false;

    if (!value || value.length > 50) {
        alertInputUsername();
        return;
    }

    isValidInputUsername = true;
    alertInputUsername(true);
}

function alertInputUsername(isCorrectCondition) {
    const input = document.querySelector("#username");
    if (!isCorrectCondition) {
        input.style.borderColor = "red";
    } else {
        input.removeAttribute("style");
    }
}

// EMAIL
function validateEmail(e) {
    const value = e.target.value;
    isEmailValid = false;

    if (!value || value.length < 5) {
        alertInputEmail();
        return;
    }

    if (
        !String(value)
            .toLowerCase()
            .match(
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            )
    ) {
        alertInputEmail();
        return;
    }

    isEmailValid = true;
    alertInputEmail(true);
}

function alertInputEmail(isCorrectCondition) {
    // console.log(isCorrectCondition);
    const inputEmail = document.querySelector('input[name="email"]');
    if (!isCorrectCondition) {
        inputEmail.style.borderColor = "red";
    } else {
        inputEmail.removeAttribute("style");
    }
}

// PASSWORD
function validatePassword(e) {
    const value = e.target.value;
    isPasswordValid = false;

    if (!value || value.length < 3) {
        alertInputPassword();
        return;
    }
    isPasswordValid = true;
    alertInputPassword(true);
    // console.log(e.target.value);
}

function alertInputPassword(isCorrectCondition) {
    const inputPassword = document.querySelector('input[name="password"]');
    if (!isCorrectCondition) {
        inputPassword.style.borderColor = "red";
    } else {
        inputPassword.removeAttribute("style");
    }
}

function openHidePassword(e) {
    const pwdEl = document.querySelector('input[name="password"]');
    const showHidePwdEl = document.querySelector("i.show-hide-pwd");
    if (pwdEl.type === "password") {
        pwdEl.type = "text";
        showHidePwdEl.className = "bi bi-eye-slash show-hide-pwd";
    } else {
        pwdEl.type = "password";
        showHidePwdEl.className = "bi bi-eye show-hide-pwd";
    }
}

// RE-PASSWORD
function validateRePassword(e) {
    const value = e.target.value;
    const inputPassword = document.querySelector(
        'input[name="re-password"]'
    ).value;
    isRePasswordValid = false;

    if (!value || value.length < 3 || value !== inputPassword) {
        alertInputRePassword();
        return;
    }
    isRePasswordValid = true;
    alertInputRePassword(true);
    // console.log(e.target.value);
}

function alertInputRePassword(isCorrectCondition) {
    const inputPassword = document.querySelector('input[name="re-password"]');
    if (!isCorrectCondition) {
        inputPassword.style.borderColor = "red";
    } else {
        inputPassword.removeAttribute("style");
    }
}

function openHideRePassword(e) {
    const pwdEl = document.querySelector('input[name="re-password"]');
    const showHidePwdEl = document.querySelector("#rePassIcon");
    if (pwdEl.type === "password") {
        pwdEl.type = "text";
        showHidePwdEl.className = "bi bi-eye-slash show-hide-pwd";
    } else {
        pwdEl.type = "password";
        showHidePwdEl.className = "bi bi-eye show-hide-pwd";
    }
}