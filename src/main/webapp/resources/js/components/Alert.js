class Alert {
    #container;
    #message;
    #type;
    #element;

    constructor(message, type = 'danger') {
        this.#container = document.getElementById('alert-container');
        this.#message = message;
        this.#type = type;
        this.#element = null;
        this.render();
    }

    get message() {
        return this.#message;
    }

    set message(value) {
        this.#message = value;
        this.#updateContent();
    }

    get type() {
        return this.#type;
    }

    set type(value) {
        this.#type = value;
        this.#updateType();
    }

    #createAlertElement() {
        const alert = document.createElement('div');
        alert.className = `alert alert-${this.#type} alert-dismissible fade show`;
        alert.setAttribute('role', 'alert');

        const message = document.createElement('span');
        message.textContent = this.#message;

        const closeButton = document.createElement('button');
        closeButton.type = 'button';
        closeButton.className = 'btn-close';
        closeButton.setAttribute('data-bs-dismiss', 'alert');
        closeButton.setAttribute('aria-label', 'Close');

        alert.appendChild(message);
        alert.appendChild(closeButton);

        return alert;
    }

    #updateContent() {
        if (this.#element) {
            const messageSpan = this.#element.querySelector('span');
            if (messageSpan) {
                messageSpan.textContent = this.#message;
            }
        }
    }

    #updateType() {
        if (this.#element) {
            this.#element.className = `alert alert-${this.#type} alert-dismissible fade show`;
        }
    }

    remove() {
        if (this.#element && this.#element.parentNode) {
            this.#element.parentNode.removeChild(this.#element);
        }
    }

    render() {
        if (!this.#container) {
            console.error('Alert container not found!');
            return;
        }

        this.remove();

        this.#element = this.#createAlertElement();
        this.#container.appendChild(this.#element);
    }
}

export default Alert;
