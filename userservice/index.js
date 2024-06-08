const express = require("express");
const bodyParser = require("body-parser");
const dotenv = require("dotenv");
const mongoose = require("mongoose");
const userRoutes = require("./routes/userRoutes");

dotenv.config();

const app = express();

app.set("view engine", "ejs");
app.set("views", __dirname + "/views");

app.use(bodyParser.json());
app.use(express.static("public"));
app.use(express.urlencoded({ extended: true })); //Body parser
app.use("/user", userRoutes);

const PORT = 3000;
const DB_URI = `mongodb+srv://jakerkane:${process.env.DB_PASSWD}@microcluster.mqyt4t2.mongodb.net/`;

mongoose
  .connect(DB_URI, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => {
    app.listen(PORT, () => {
      console.log(`User service running on port ${PORT}`);
    });
  })
  .catch((err) => {
    console.error("Database connection error:", err);
  });
