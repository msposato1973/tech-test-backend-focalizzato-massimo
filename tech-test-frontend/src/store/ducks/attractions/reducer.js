// Redux
import { createReducer } from '@reduxjs/toolkit';

// Actions
import { attractionActions } from './actions';

export const initialState = {
    attractionList: [],
    error: null,
};

const attractionsReducer = createReducer(initialState, {
    [attractionActions.getAttractions.type]: (state) => {
        state.error = null;
    },
    [attractionActions.getAttractionsSuccess.type]: (state, {payload: {data}}) => {
        state.attractionList = data;
        state.error= null;
    },
    [attractionActions.getAttractionsFailure.type]: (state, {payload: {data}}) => {
        state.error = data;
    },
});

export default attractionsReducer;
