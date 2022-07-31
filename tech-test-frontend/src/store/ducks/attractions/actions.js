import { createAction } from '@reduxjs/toolkit';

export const ns = 'attraction';

const getAttractions = createAction(`${ns}/getAttractions`, function prepare(data) {
    return {
        payload: data,
    };
});

const getAttractionsSuccess = createAction(`${ns}/getAttractionsSuccess`, function prepare(data) {
    return {
        payload: data,
    };
});

const getAttractionsFailure = createAction(`${ns}/getAttractionsFailure`, function prepare(data) {
    return {
        payload: data,
    };
});


export const attractionActions = {
    getAttractions,
    getAttractionsSuccess,
    getAttractionsFailure,
};
