export default function() {
  return function(req, res, next) {
    if (req.method === "GET" && req.path === "/") {
      res.redirect(302, "/index.html");
      return;
    }

    next();
  };
}
