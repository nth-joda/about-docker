import { Grid, Typography } from "@mui/material";
import React from "react";
import HvButton from "../../components/Buttons/HoverButton";
import NnButton from "../../components/Buttons/NeonButton";

const Content = () => {
  return (
    <Grid container>
      <Grid item md={12}>
        <Typography sx={{ fontSize: "1.2rem", fontWeight: 600 }}>
          Hover Buttons
        </Typography>
        <Grid container justifyContent="center" columnSpacing={2}>
          <HvButton type="solid-up">solid-up</HvButton>
          <HvButton type="solid-down">solid-down</HvButton>
          <HvButton type="transparent-up">transparent-up</HvButton>
          <HvButton type="transparent-down">transparent-down</HvButton>
        </Grid>
      </Grid>

      <Grid item md={12}>
        <Typography sx={{ fontSize: "1.2rem", fontWeight: 600 }}>
          Neon Buttons
        </Typography>
        <Grid container justifyContent="center" columnSpacing={2}>
          <NnButton>Neon</NnButton>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Content;
