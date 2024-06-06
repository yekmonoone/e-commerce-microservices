const express = require("express");
const {
  register,
  registerPost,
  loginPost,
  login,
  getProfile,
  getUser,
  logOut,
} = require("../controllers/userController");
const { authMiddleware } = require("../middleware/authMiddleware");

const router = express.Router();

router.post("/register", registerPost);
router.post("/login", loginPost);
router.get("/register", register);
router.get("/login", login);
router.get("/profile/:userId", authMiddleware, getProfile);
router.get("/:id", authMiddleware, getUser);
router.get("/logout", authMiddleware, logOut);

module.exports = router;
