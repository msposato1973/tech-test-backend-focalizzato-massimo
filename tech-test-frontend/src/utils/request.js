export const handleResponse = (response) =>
    response.text().then((text) => {
        const data = text && JSON.parse(text);

        if (!response.ok) {
            return Promise.reject(data);
        }

        return data;
    });

export const getData = (url, options = {}) => {
    const requestOptions = {
        method: 'GET',
        ...options
    };

    return fetch(url, requestOptions).then(handleResponse);
};