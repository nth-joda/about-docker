import React from "react";
import "./App.css";
import styled from "styled-components";
import SideBar from "./components/SideBar";
import MainContent from "./components/MainContent";

function App() {
  return (
    <Container>
      <SideBar></SideBar>
      <MainContent></MainContent>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  height: 97vh;
  background: linear-gradient(to bottom right, white 0%, #e6e4ff 70%);
  border-radius: 2rem;
`;

export default App;
