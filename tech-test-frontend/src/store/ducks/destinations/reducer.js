// Redux
import { createReducer } from '@reduxjs/toolkit';

// Actions
import { destinationActions } from './actions';

export const initialState = {
    destinationList: [],
    pageNumber: 0,
    pageSize: 30,
    total: 0,
    error: null,
};

const destinationsReducer = createReducer(initialState, {
    [destinationActions.getDestinations.type]: (state) => {
        state.error = null;
    },
    [destinationActions.getDestinationsSuccess.type]: (state, {payload: {data}}) => {
        state.destinationList = data.results;
        state.pageNumber = data.pageNumber;
        state.pageSize = data.pageSize;
        state.total = data.total;
        state.error = null;
    },
    [destinationActions.getDestinationsFailure.type]: (state, {payload: {data}}) => {
        state.error = data;
    },
});

export default destinationsReducer;
