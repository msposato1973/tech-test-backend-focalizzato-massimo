import { call, put, takeLatest } from 'redux-saga/effects';
import { getData } from '../../utils/request';
import { destinationActions } from '../ducks/destinations/actions';

export function* getDestinations() {
    try {
        const response = yield call(getData, `http://localhost:8080/destinations?pageSize=100&pageNumber=0`);
        yield put({
            type: destinationActions.getDestinationsSuccess.type,
            payload: {
                data: response
            },
        });
    } catch (e) {
        yield put({
            type: destinationActions.getDestinationsFailure.type,
            payload: {
                data: e.error
            }
        });
    }
}


function* watchDataRequest() {
    yield takeLatest(destinationActions.getDestinations, getDestinations);
}

export default watchDataRequest;
