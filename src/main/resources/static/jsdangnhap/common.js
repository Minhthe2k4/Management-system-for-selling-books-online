window.addEventListener("load", function () {
    const loginForm = document.querySelector(".login-form");
    const showPasswordIcon =
        loginForm && loginForm.querySelector(".show-password");
    const inputPassword =
        loginForm && loginForm.querySelector('input[type="password"');
    showPasswordIcon.addEventListener("click", function () {
        const inputPasswordType = inputPassword.getAttribute("type");
        inputPasswordType === "password"
            ? inputPassword.setAttribute("type", "text")
            : inputPassword.setAttribute("type", "password");
    });
});
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector(".login-form");
    const inputs = form.querySelectorAll(".auth-form-input");
    const showPasswordIcon = document.querySelector(".show-password");
    const passwordInput = form.querySelector("input[name='passDangKi']");
    const confirmPasswordInput = form.querySelector("input[name='passDangKi2']");

    // Hàm hiển thị lỗi
    function showError(input, message) {
        const errorElement = document.createElement("div");
        errorElement.className = "error-message";
        errorElement.textContent = message;
        input.parentElement.appendChild(errorElement);
        input.classList.add("input-error");
    }

    // Hàm xóa lỗi
    function clearErrors() {
        const errorMessages = document.querySelectorAll(".error-message");
        errorMessages.forEach((error) => error.remove());
        inputs.forEach((input) => input.classList.remove("input-error"));
    }

    // Kiểm tra email hợp lệ
    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    // Sự kiện kiểm tra trước khi submit
    form.addEventListener("submit", function (e) {
        clearErrors();
        let isValid = true;

        inputs.forEach((input) => {
            if (input.value.trim() === "") {
                isValid = false;
                showError(input, "Trường này không được để trống.");
            }
        });

        const emailInput = form.querySelector("input[name='emailDangKi']");
        if (!validateEmail(emailInput.value)) {
            isValid = false;
            showError(emailInput, "Email không hợp lệ.");
        }

        if (passwordInput.value.length < 6) {
            isValid = false;
            showError(passwordInput, "Mật khẩu phải có ít nhất 6 ký tự.");
        }

        if (passwordInput.value !== confirmPasswordInput.value) {
            isValid = false;
            showError(confirmPasswordInput, "Mật khẩu không khớp.");
        }

        if (!isValid) {
            e.preventDefault(); // Ngăn form submit nếu có lỗi
        }
    });

    // Sự kiện hiển thị/ẩn mật khẩu
    showPasswordIcon.addEventListener("click", function () {
        const type = passwordInput.getAttribute("type") === "password" ? "text" : "password";
        passwordInput.setAttribute("type", type);
        confirmPasswordInput.setAttribute("type", type);
        this.classList.toggle("fa-eye-slash");
    });
});