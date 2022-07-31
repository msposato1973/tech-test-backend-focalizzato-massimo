import { useRouter } from "next/router";
import Head from "next/head";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import Header from "../../components/Header";
import { attractionActions } from "../../store/ducks/attractions/actions";
import { getAttractionError, getAttractionList } from "../../store/selectors/attractions";
import { getDestinationList } from "../../store/selectors/destinations";
import { Container, Error } from "../../styles/home";
import {
  Attractions,
  Attraction,
  Name,
  Percentage,
  Destination,
  TotalAttractions,
  Heading,
} from "../../styles/attractions";

const DestinationPage = () => {
  const router = useRouter();
  const dispatch = useDispatch();
  const { destinationID } = router.query;
  const attractionList = useSelector(getAttractionList);
  const destinationList = useSelector(getDestinationList);
  const attractionError = useSelector(getAttractionError);

  useEffect(() => {
    if (destinationID)
      dispatch(attractionActions.getAttractions(destinationID));
  }, [destinationID]);

  const destinationName = destinationList.find(
    (dest) => dest.id === destinationID
  )?.name;

  return (
    <Container>
      <Head>
        <title>{destinationName}</title>
        <link rel="icon" href="/assets/favicon.ico" />
      </Head>
      <Header />
      {attractionError && <Error>Oops! Something went wrong: {attractionError}</Error>}
      <Heading>
        <Destination>
          <h1>{destinationName}</h1>
          <TotalAttractions>{attractionList?.length}</TotalAttractions>
        </Destination>
      </Heading>

      <Attractions>
        {attractionList?.map((attraction) => {
          const { id, name, visitPercentage } = attraction;
          return (
            <Attraction data-testid="attractionItem" key={id}>
              <Name>{name}</Name>
              <Percentage>{visitPercentage}%</Percentage>
            </Attraction>
          );
        })}
      </Attractions>
    </Container>
  );
};

export default DestinationPage;
