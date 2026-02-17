# Task-Sheduling-System
📌 ProManage Solutions – Project Scheduling System
📖 Overview
ProManage Solutions Pvt. Ltd. is a project management company that handles multiple client software projects such as UI design, development, testing, and deployment.

This system automates project scheduling to maximize profit while following business constraints:

Company operates 5 days per week (Monday–Friday)

Maximum 5 projects per week

Only 1 project per day

Projects must be completed before their deadline

If a deadline is missed → revenue is lost

The goal of this system is to automatically select and schedule the most profitable projects within the allowed time constraints.

🎯 Problem Statement
Each project contains:

Project ID (auto-generated)

Title

Deadline (in working days)

Expected Revenue

Example:
If deadline = 3
→ The project must be completed within the first 3 working days.

The system must:

Select projects strategically

Ensure deadlines are not violated

Maximize total weekly revenue

Schedule no more than 5 projects per week

🧠 Solution Approach
This project uses a Greedy Algorithm (Job Sequencing with Deadlines):

Steps:
Sort projects by highest revenue first

Try assigning each project to the latest available day before its deadline

If slot is free → schedule it

If no slot available → skip project

This guarantees maximum profit under given constraints.

🏗 Tech Stack
Backend
Java

JDBC
PostgreSQL

Database
PostgreSQL 18


pgAdmin
