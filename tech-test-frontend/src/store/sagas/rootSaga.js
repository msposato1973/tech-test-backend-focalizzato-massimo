import { all } from 'redux-saga/effects';
import destinationsSaga from './destinationsSaga';
import attractionsSaga from './attractionsSaga';

const sagas = [
    destinationsSaga(),
    attractionsSaga()
];

function* rootSaga() {
    yield all(sagas);
}

export default rootSaga;
