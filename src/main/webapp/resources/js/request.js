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
            const errorType = xhr.getResponseHeader("x-error-type") ?? "";
            const errorMessage = xhr.getResponseHeader("x-error-message") ?? "";

            if(errorType && errorMessage) {
                let alertType = 'danger';
                let alertMessage = '';

                switch (errorType?.trim()?.toUpperCase()) {
                    case 'VALIDATION':
                        alertMessage = `A validation error occurred: ${errorMessage}. Please check the input and try again.`;
                        break;
                    default:
                        alertMessage = `An unexpected error has occurred: ${errorMessage}. Please try again later.`;
                        break;
                }

                new Alert(alertMessage, alertType);
            }
        });
    }
}

export default new Request();