# Limit Order Book Matching Engine (Java)

A high-performance single-threaded Limit Order Book implemented in Java, simulating core exchange matching logic with price-time priority and performance benchmarking.

---

## Overview

This project implements a simplified exchange matching engine that processes limit and market orders using **price-time priority**.
It supports order matching, partial fills, cancellation, trade logging, market depth visualization, and performance measurement.

The system is designed to simulate high-throughput order flow and measure low-latency execution, similar to core components used in trading systems.

---

## Features

* Price-Time Priority Matching
* Limit Order Processing
* Market Order Processing
* Partial Order Fills
* Order Cancellation (O(1) lookup using HashMap)
* Trade Logging
* Performance Benchmarking using `System.nanoTime()`

---

## Architecture

```
Main
 ├── OrderGenerator   (Simulates market order flow)
 ├── OrderManager     (Entry point for order handling)
 ├── OrderBook        (Matching engine + data structures)
 └── TradeLogger      (Trade recording)
```

### Core Data Structures

* `TreeMap<Double, LinkedList<Order>>` — Price levels (bids & asks)
* `HashMap<Long, Order>` — Fast order lookup
* FIFO queues for time priority within each price level

---

## Order Matching Logic

### Price Priority

* Highest bid matched first
* Lowest ask matched first

### Time Priority

* Orders at the same price are executed in FIFO order

### Partial Fill

If order quantities differ, the smaller quantity is executed and the remaining order stays in the book.

---

## Performance

Tested with synthetic order flow:

* **Total Orders:** 1,000,000
* **Throughput:** ~2.3 million orders/sec
* **Average Latency:** ~0.4 microseconds per order

*Note: Trade console logging disabled during performance testing.*

---

## Project Structure

```
com.lob
 ├── model
 │    ├── Order.java
 │    ├── OrderSide.java
 │    ├── OrderType.java
 │    └── Trade.java
 │
 ├── engine
 │    └── OrderBook.java
 │
 ├── service
 │    └── OrderManager.java
 │
 ├── simulator
 │    └── OrderGenerator.java
 │
 ├── util
 │    └── TradeLogger.java
 │
 └── Main.java
```

---

## How to Run

1. Clone the repository
2. Open the project in your IDE (IntelliJ / VS Code)
3. Run:

```
Main.java
```

The program will:

* Simulate 1,000,000 random orders
* Execute matching
* Print performance metrics
* Display top order book levels

---

## Concepts Demonstrated

* Market Microstructure Basics
* Limit Order Book Design
* Matching Engine Logic
* Low-latency System Design
* Throughput & Latency Measurement
* Efficient Data Structures for Trading Systems

---

## Future Improvements

* Multithreading (Producer–Consumer model)
* Lock-free queues
* Array-based order book (price indexing)
* Order status tracking (NEW / PARTIAL / FILLED)
* Persistence or event streaming

---

## Author

Prem Shirvi
B.Tech CSE | Interested in Low-Latency Systems, Quantitative Finance & Trading Infrastructure
