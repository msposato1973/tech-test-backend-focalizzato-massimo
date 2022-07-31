import { combineReducers } from 'redux';
import attractionReducer from './attractions/reducer';
import destinationReducer from './destinations/reducer';

export const rootReducer = combineReducers({
  destination: destinationReducer,
  attraction: attractionReducer
});

