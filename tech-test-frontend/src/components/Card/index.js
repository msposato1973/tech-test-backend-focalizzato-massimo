import React from "react";
import Image from "next/image";
import PropTypes from "prop-types";
import { useRouter } from "next/router";
import { Wrapper, ImageContainer, Title } from "./styles";

const Card = ({ destination, destinationID, imageSrc }) => {
  const router = useRouter();
  return (
    <Wrapper onClick={() => router.push(`/destination/${destinationID}`)} data-testid="cardComponent">
      <ImageContainer>
        <Image alt={destination} src={imageSrc} width={1000} height={1000} data-testid="card-image" />
      </ImageContainer>
      <Title>
        <h2>{destination}</h2>
      </Title>
    </Wrapper>
  );
};

Card.propTypes = {
  destinationID: PropTypes.string.isRequired,
  imageSrc: PropTypes.string.isRequired,
  destination: PropTypes.string.isRequired,
};

export default Card;
