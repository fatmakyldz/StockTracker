# 📦 StockTracker – Java-Based Product Stock Notification System

StockTracker is a Java-based terminal application that allows users to monitor product stock availability across multiple e-commerce platforms.  
The system enables users to register, add products by site and product code, receive notifications, and manage their watchlist from the terminal.

🎓 Developed as a term project for **SEN3006 - Software Architecture** course at Bahçeşehir University

---

## 🚀 Features

- 🔐 Case-sensitive user registration and login system
- 📦 Add/remove products to watchlist by site name and product ID
- 🛒 If stock is available → simulate redirection to product/cart
- ⏱ If out-of-stock → product added to watchlist and auto-checked in background
- 📧 Email notification sent to user when stock becomes available
- 🔄 Periodic background stock monitoring (via `StockMonitorTask`)
- 🌐 Supported Inditex sites: Zara, Pull&Bear, Bershka, Stradivarius, Oysho, Massimo Dutti
- 🧹 Clear session exit, multi-product support, no auto logout

---

## 🧠 Architecture & Design Patterns

- 🧩 **Observer Pattern** – To notify users when stock status changes  
- 🔒 **Singleton Pattern** – To manage shared resources and user session state  
- 📐 Layered architecture – separated into `model`, `manager`, `task`, `util`, and `main`

---

## 🗃️ Project Structure

```
StockTracker/
├── model/
│   ├── Product.java
│   └── AppUser.java
├── manager/
│   ├── UserManager.java
│   ├── ProductManager.java
│   └── StockChecker.java
├── task/
│   └── StockMonitorTask.java
├── util/
│   └── EmailSender.java
├── main/
│   └── Main.java
├── resources/
│   └── products.txt
└── README.md
```

---

## 🛠 Technologies Used

- **Java 17**
- **Jsoup** – HTML parsing and web scraping for product stock
- **javax.mail** – Email sending for stock alert
- **TimerTask** – For periodic background checks
- **OOP principles** – Class-based separation and modularity

---

## 📌 Sample Terminal Flow

```
Welcome to StockTracker!
1. Register
2. Login
> 2
Username: fatma
Password: *****

Welcome, fatma!

1. Add Product
2. View Watchlist
3. Remove Product
4. Logout
> 1
Enter site (e.g., zara, bershka): zara
Enter product code: 01234567

Product currently out of stock. Added to your watchlist.
(You will be notified when it comes back in stock.)
```

---

## 📧 Email Notification Example

> Subject: 🔔 Stock Alert – Product Back in Stock!  
> "Hi Fatma, your tracked product at Zara (ID: 01234567) is now available. Click to buy before it’s gone!"

---

## 👩‍💻 Author

**Fatma Akyıldız**  
Final-year Software Engineering student | Backend & Automation Enthusiast  
[GitHub](https://github.com/fatmakyldz) | [LinkedIn](https://www.linkedin.com/in/fatma-akyıldız)

---

## 📜 License

This project is open-source for educational and personal use.  
Do not reuse scraping logic for commercial purposes without compliance with target site terms.
