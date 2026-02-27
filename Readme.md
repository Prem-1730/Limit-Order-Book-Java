# Limit Order Book Matching Engine (Java)

A high-performance single-threaded Limit Order Book implemented in Java.  
Simulates exchange matching logic using **price-time priority**.

---

## Overview
This project implements:
- Limit order processing
- Market order processing
- Partial fills
- Order cancellation
- Trade logging
- Performance benchmarking

Designed to simulate high-throughput trading systems.

---

## Features
- Price-Time Priority Matching
- Limit Order Processing
- Market Order Processing
- Partial Order Fills
- Order Cancellation (O(1) using HashMap)
- Trade Logging
- Performance Measurement

---

## Architecture

Main Components:
- **OrderGenerator** – Simulates market flow
- **OrderManager** – Entry point for orders
- **OrderBook** – Matching engine
- **TradeLogger** – Records trades

---

## Core Data Structures
- `TreeMap` – Price levels (bids & asks)
- `HashMap` – Fast order lookup
- FIFO Queues – Time priority within price level

---

## Performance
Tested with synthetic order flow:
- Total Orders: 1,000,000+
- Throughput: ~2.3M orders/sec
- Average Latency: ~0.4 microseconds

---

## Project Structure

com.lob

├── model

├── engine

├── service

├── simulator

├── util

└── Main.java


---

## How to Run

1. Clone the repository
2. Open in VS Code / IntelliJ
3. Run:

  Main.java

 Program will:
- Simulate random orders
- Execute matching
- Print performance metrics


