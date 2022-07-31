import { createAction } from '@reduxjs/toolkit';

export const ns = 'destination';

const getDestinations = createAction(`${ns}/getDestinations`, function prepare(data) {
    return {
        payload: data,
    };
});

const getDestinationsSuccess = createAction(`${ns}/getDestinationsSuccess`, function prepare(data) {
    return {
        payload: data,
    };
});

const getDestinationsFailure = createAction(`${ns}/getDestinationsFailure`, function prepare(data) {
    return {
        payload: data,
    };
});


export const destinationActions = {
    getDestinations,
    getDestinationsSuccess,
    getDestinationsFailure,
};
