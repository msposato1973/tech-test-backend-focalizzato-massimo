import styled from "styled-components";

export const Heading = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;

  @media (min-width: 640px) {
    justify-content: space-between;
  }
`;

export const Destination = styled.div`
  display: flex;
  align-items: center;
`;

export const Attractions = styled.div`
  width: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(15rem, 1fr));
  gap: 1.25rem;
  margin-top: 0.9375rem;
`;

export const Attraction = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 0.0625rem solid transparent;
  border-radius: 0.625rem;
  background: #eff0f6;
  padding: 0 0.625rem;
`;

export const Name = styled.h3`
  text-transform: capitalize;
`;

export const Percentage = styled.span``;

export const TotalAttractions = styled.div`
  height: fit-content;
  margin-left: 0.3125rem;
  border: 0.0625rem solid transparent;
  border-radius: 0.3125rem;
  padding: 0.3125rem;
  background: #24249b;
  color: #eff0f6;
`;
