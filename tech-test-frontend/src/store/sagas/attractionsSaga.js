import { call, put, takeLatest } from 'redux-saga/effects';
import { getData } from '../../utils/request';
import { attractionActions } from '../ducks/attractions/actions';

export function* getAttractions({payload: data}) {
    try {
        const response = yield call(getData, `http://localhost:8080/attractions?destinationId=${data}`);
        yield put({
            type: attractionActions.getAttractionsSuccess.type,
            payload: {
                data: response
            }
        });
    } catch (e) {
        yield put({
            type: attractionActions.getAttractionsFailure.type,
            payload: {
                data: e.error
            }
        });
    }
}


function* watchDataRequest() {
    yield takeLatest(attractionActions.getAttractions, getAttractions);
}

export default watchDataRequest;
