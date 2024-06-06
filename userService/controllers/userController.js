const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const User = require("../models/userModel");
const axios = require("axios");

const register = async (req, res) => {
  try {
    res.render("register");
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const login = async (req, res) => {
  try {
    res.render("login");
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const registerPost = async (req, res) => {
  const { username, email, password, cpassword } = req.body;
  console.log(req.body);
  try {
    if (password != cpassword) {
      return res.render("register", { error: "Passwords don't match !" });
    }
    const copyUser = await User.findOne({ email: email, username: username });
    if (copyUser) {
      return res.render("register", {
        error: "This credentials are used before. Please try again !",
      });
    }
    const hashedPassword = await bcrypt.hash(password, 10);
    const user = new User({ username, email, password: hashedPassword });
    await user.save();
    const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET, {
      expiresIn: "1h",
    });
    res.cookie("token", token, { httpOnly: true });
    res.send("token");
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const loginPost = async (req, res) => {
  const { email, password } = req.body;
  try {
    console.log(req.body);
    const user = await User.findOne({ email });
    if (!user || !(await bcrypt.compare(password, user.password))) {
      return res.render("login", { error: "Invalid credentials" });
    }
    const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET, {
      expiresIn: "1h",
    });
    res.cookie("token", token, {
      httpOnly: true,
    });
    res.json(user);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const getProfile = async (req, res) => {
  const { userId } = req.params;
  try {
    const user = await User.findById(userId);

    const userNotifications = await axios.get(
      `localhost:8082/notifications/user/${userId}`
    );

    const userOrders = await axios.get(
      `localhost:8080/api/orders/getOrdersByUserId/${userId}`
    );

    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }
    res.render("user", {
      notifications: userNotifications,
      orders: userOrders,
      user: user,
    });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const getUser = async (req, res) => {
  try {
    const id = req.params.userid;
    const user = await User.findById(id);
    res.json(user);
  } catch (error) {
    res.status(400).json({ error: "User Not Found" });
  }
};

const logOut = async (req, res) => {
  try {
    res.clearCookie("token");
    res.redirect("/user/login");
  } catch (error) {
    res.status(400).json({ error: "Error accured" });
  }
};

module.exports = {
  register,
  login,
  getProfile,
  registerPost,
  loginPost,
  getUser,
  logOut,
};
