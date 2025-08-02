import Alert from './components/Alert.js';

class Request {
    #bodyElement = document.body;
    #AFTER_REQUEST_EVENT = "htmx:afterRequest";

    initListeners() {
        this.#initAfterRequest();
    }

    #initAfterRequest() {
        this.#bodyElement.addEventListener(this.#AFTER_REQUEST_EVENT, (event) => {
            const xhr = event.detail.xhr;
            const { errorType, errorMessage } = this.#getErrorHeaders(xhr);

            if(errorType && errorMessage) {
                const alertType = 'danger';
                const alertMessage = this.#getValidationErrorMessage(xhr);
                new Alert(alertMessage, alertType);
            }
        });
    }

    #getErrorHeaders(xhr) {
        const errorType = xhr.getResponseHeader("x-error-type") ?? "";
        const errorMessage = xhr.getResponseHeader("x-error-message") ?? "";
        return { errorType, errorMessage };
    }

    #getValidationErrorMessage(xhr) {
        const { errorType, errorMessage } = this.#getErrorHeaders(xhr);

        console.log(99,errorType, errorMessage)

        switch (errorType?.trim()?.toUpperCase()) {
            case 'VALIDATION':
                return `A validation error occurred: ${errorMessage}. Please check the input and try again.`;
            default:
                return `An unexpected error has occurred: ${errorMessage}. Please try again later.`;
        }
    }
}

export default new Request();