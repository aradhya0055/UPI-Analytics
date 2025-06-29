# 🚀 UPI Analytics Pro

A modern, professional UPI transaction analytics dashboard built with Spring Boot, featuring real-time multi-currency transaction monitoring, interactive charts, and a beautiful dark theme interface.

![UPI Analytics Pro](https://img.shields.io/badge/Spring%20Boot-3.5.3-green)
![Java](https://img.shields.io/badge/Java-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.3-purple)
![Chart.js](https://img.shields.io/badge/Chart.js-4.0-yellow)

## ✨ Features

### 🎨 **Modern Dark Theme**
- Professional dark interface with gradient accents
- Responsive design that works on all devices
- Smooth animations and hover effects
- Modern card-based layout

### 📊 **Real-time Analytics**
- **Interactive Charts**: Monthly trends, country distribution, currency analysis
- **Live Statistics**: Total volume, transaction count, unique countries
- **Multi-Currency Support**: 20+ countries with automatic exchange rates
- **Transaction Timeline**: Visual representation of transaction patterns

### 💰 **Multi-Currency Support**
- **20+ Countries**: India, USA, UK, EU, Japan, Australia, Canada, and more
- **Automatic Conversion**: Real-time INR conversion rates
- **Currency Detection**: Automatic currency assignment based on country
- **Exchange Rate Management**: Built-in exchange rate system

### 🔧 **Transaction Management**
- **Add Transactions**: Easy form-based transaction entry
- **View All Transactions**: Complete transaction history with filtering
- **Delete Transactions**: Individual and bulk deletion options
- **Real-time Updates**: Instant data refresh after operations

### 📱 **Responsive Design**
- **Mobile-Friendly**: Optimized for smartphones and tablets
- **Desktop Optimized**: Full-featured experience on larger screens
- **Cross-Browser**: Works on Chrome, Firefox, Safari, Edge

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.5.3
- **Database**: MySQL 8.0
- **Frontend**: Thymeleaf, Bootstrap 5.3.3, Chart.js
- **Styling**: Custom CSS with modern gradients and animations
- **Icons**: Font Awesome 6.0
- **Build Tool**: Maven

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/upi-analytics-pro.git
   cd upi-analytics-pro
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE upi_tracker;
   ```

3. **Configure Database**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/upi_tracker
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the Application**
   Open your browser and go to: `http://localhost:8082`

## 📋 Application Structure

```
src/
├── main/
│   ├── java/com/upi/tracker/
│   │   ├── controller/
│   │   │   └── TransactionController.java
│   │   ├── entity/
│   │   │   └── Transaction.java
│   │   ├── repository/
│   │   │   └── TransactionRepository.java
│   │   └── TrackerApplication.java
│   └── resources/
│       ├── templates/
│       │   ├── dashboard.html
│       │   ├── transactions.html
│       │   └── analytics.html
│       └── application.properties
```

## 🎯 Key Features Explained

### Dashboard (`/`)
- **Statistics Cards**: Real-time overview of total volume, transactions, and countries
- **Transaction Form**: Add new transactions with multi-currency support
- **Interactive Charts**: Country distribution, volume trends, monthly patterns
- **Recent Transactions**: Latest 5 transactions with delete functionality

### All Transactions (`/transactions`)
- **Complete History**: View all transactions in a modern table
- **Delete Options**: Remove individual transactions
- **Empty State**: Helpful message when no transactions exist
- **Responsive Table**: Works perfectly on all screen sizes

### Analytics (`/analytics`)
- **4 Interactive Charts**:
  - Monthly expenditure trends
  - Country distribution (doughnut chart)
  - Currency distribution (pie chart)
  - Transaction timeline (bar chart)
- **Statistics Overview**: Key metrics with beautiful cards
- **Real-time Data**: Charts update with actual transaction data

## 💡 Usage Examples

### Adding a Transaction
1. Go to the main dashboard
2. Fill in the transaction form:
   - **Sender UPI ID**: `user@upi`
   - **Receiver UPI ID**: `merchant@upi`
   - **Amount**: `1000`
   - **Country**: Select from dropdown (e.g., "United States")
   - **Purpose**: `Shopping` (optional)
3. Click "Add Transaction"

### Viewing Analytics
1. Click "Analytics" in the navigation
2. Explore the interactive charts
3. Hover over chart elements for detailed information
4. View statistics cards for quick insights

### Managing Transactions
1. Click "All Transactions" to see complete history
2. Use the delete button (🗑️) to remove individual transactions
3. Use "Clear All" button to remove all transactions

## 🎨 Customization

### Changing Colors
Edit the CSS variables in any template file:
```css
:root {
    --dark-bg: #0f0f23;
    --card-bg: #1a1a2e;
    --accent-blue: #4f46e5;
    --accent-green: #10b981;
}
```

### Adding New Countries
Edit `TransactionController.java` to add new countries and exchange rates:
```java
case "New Country":
    currency = "NEW";
    exchangeRate = 0.050;
    break;
```

## 🔧 Configuration

### Port Configuration
Change the server port in `application.properties`:
```properties
server.port=8082
```

### Database Configuration
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 📊 API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/` | GET | Main dashboard |
| `/transactions` | GET | All transactions page |
| `/analytics` | GET | Analytics dashboard |
| `/add` | POST | Add new transaction |
| `/delete/{id}` | DELETE | Delete specific transaction |
| `/clear-all` | DELETE | Delete all transactions |
| `/test` | GET | Health check endpoint |

## 🚀 Deployment

### Local Development
```bash
./mvnw spring-boot:run
```

### Production Build
```bash
./mvnw clean package
java -jar target/tracker-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/tracker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Spring Boot** for the robust backend framework
- **Bootstrap** for the responsive UI components
- **Chart.js** for the beautiful interactive charts
- **Font Awesome** for the professional icons
- **Thymeleaf** for the server-side templating

## 📞 Support

If you have any questions or need help, please open an issue on GitHub or contact the development team.

---

**Made with ❤️ for UPI transaction analytics**

*UPI Analytics Pro - Where Data Meets Design* 