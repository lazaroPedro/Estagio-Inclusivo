document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");
    const nascimentoInput = document.querySelector("input[name='nascimento']");
    const inicioCursoInput = document.querySelector("input[name='curso_inicio']");
    const fimCursoInput = document.querySelector("input[name='curso_fim']");
    const cpfInput = document.querySelector("input[name='cpf']");
    const cepInput = document.querySelector("input[name='cep']");
    const emailInput = document.querySelector("input[name='email']");
    const telefoneInput = document.querySelector("input[name='telefone']");
    const errorMessages = {};

    function showError(input, message) {
        removeError(input);
        const error = document.createElement("div");
        error.className = "text-danger mt-1 small error-message";
        error.innerText = message;
        input.classList.add("is-invalid");
        input.parentElement.appendChild(error);
        errorMessages[input.name] = message;
    }

    function removeError(input) {
        const existing = input.parentElement.querySelector(".error-message");
        if (existing) existing.remove();
        input.classList.remove("is-invalid");
        delete errorMessages[input.name];
    }

    function validarDatas() {
        const hoje = new Date();
        const nascimento = new Date(nascimentoInput.value);
        const inicioCurso = new Date(inicioCursoInput.value);
        const fimCurso = new Date(fimCursoInput.value);

        removeError(nascimentoInput);
        removeError(inicioCursoInput);
        removeError(fimCursoInput);

        if (nascimentoInput.value) {
            const idade = hoje.getFullYear() - nascimento.getFullYear();
            if (nascimento > hoje) {
                showError(nascimentoInput, "sai dae exterminador do futuro");
            } else if (idade > 120) {
                showError(nascimentoInput, "Vai la silvio santos, ta muito velho pra fazer estagio.");
            }
        }

        if (inicioCursoInput.value && fimCursoInput.value) {
            if (inicioCurso > fimCurso) {
                showError(inicioCursoInput, "A data de início não pode ser depois da de término.");
                showError(fimCursoInput, "A data de término não pode ser antes da de início.");
            }
        }

        if (nascimentoInput.value && inicioCursoInput.value) {
            const idadeInicioCurso = inicioCurso.getFullYear() - nascimento.getFullYear();
            if (idadeInicioCurso < 10) {
                showError(inicioCursoInput, "virou o nicolas tesla maldito? ta cedo de mais pra fazer faculdade");
            }
        }
    }

    function validarCPF(cpf) {
        cpf = cpf.replace(/[^\d]+/g, '');
        if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;
        let soma = 0;
        for (let i = 0; i < 9; i++) soma += parseInt(cpf.charAt(i)) * (10 - i);
        let resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        if (resto !== parseInt(cpf.charAt(9))) return false;
        soma = 0;
        for (let i = 0; i < 10; i++) soma += parseInt(cpf.charAt(i)) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        return resto === parseInt(cpf.charAt(10));
    }

    cpfInput.addEventListener("blur", () => {
        removeError(cpfInput);
        if (!validarCPF(cpfInput.value)) {
            showError(cpfInput, "CPF invalido.")
        }
    });

    emailInput.addEventListener("blur", () => {
        removeError(emailInput);
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regex.test(emailInput.value)) {
            showError(emailInput, "E-mail inválido.");
        }
    });

    cepInput.addEventListener("blur", () => {
        removeError(cepInput);
        const regex = /^\d{5}-?\d{3}$/;
        if (!regex.test(cepInput.value)) {
            showError(cepInput, "CEP inválido. Use o formato 00000-000.");
        }
    });

    telefoneInput.addEventListener("blur", () => {
        removeError(telefoneInput);
        const regex = /^\(?\d{2}\)?\s?\d{4,5}-?\d{4}$/;
        if (!regex.test(telefoneInput.value)) {
            showError(telefoneInput, "Telefone inválido. Ex: (77) 98888-8888");
        }
    });

    function aplicarMascara(input, mascara) {
        input.addEventListener("input", () => {
            let i = 0;
            const v = input.value.replace(/\D/g, "");
            input.value = mascara.replace(/#/g, _ => v[i++] || "");
        });
    }

    aplicarMascara(cpfInput, "###.###.###-##");
    aplicarMascara(cepInput, "#####-###");
    aplicarMascara(telefoneInput, "(##) #####-####");

    nascimentoInput.addEventListener("blur", validarDatas);
    inicioCursoInput.addEventListener("blur", validarDatas);
    fimCursoInput.addEventListener("blur", validarDatas);

    form.addEventListener("submit", function (e) {
        validarDatas();
        if (Object.keys(errorMessages).length > 0) {
            e.preventDefault();
        }
    });
});
