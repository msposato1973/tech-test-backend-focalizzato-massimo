import Head from "next/head";
import Card from "../components/Card";

import { Container, Main, Title, Grid, Error } from "../styles/home";
import { useSelector } from "react-redux";
import { getDestinationError, getDestinationList } from "../store/selectors/destinations";
import Header from "../components/Header";

export default function Home() {
  const destinationList = useSelector(getDestinationList);
  const destinationError = useSelector(getDestinationError);

  return (
    <Container>
      <Head>
        <title>Destination Dashboard</title>
        <meta name="description" content="Generated by create next app" />
        <link rel="icon" href="/assets/favicon.ico" />
      </Head>
      <Header />
      {destinationError && <Error>Oops! Something went wrong: {destinationError}</Error>}
      <Main>
        <Title>Destinations</Title>

        <Grid>
          {destinationList?.map((destination) => {
            return (
              <Card
                key={destination.id}
                destination={destination.name}
                destinationID={destination.id}
                imageSrc={destination.imageUrl}
              />
            );
          })}
        </Grid>
      </Main>
    </Container>
  );
}
