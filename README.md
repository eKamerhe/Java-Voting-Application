# Java-Voting-Application
A very simple Swing-based Java application for voting.

# Online Voter Application

## Overview

This Java Swing application is a simple voting system that allows users to:
- Submit votes for one of three generational parties (Millennials, Gen Z, or Gen Alpha)
- View current voting results
- Prevent duplicate voting through ID verification

## Features

- **User-friendly GUI** with form validation for:
  - Name (letters only)
  - Phone number (10 digits)
  - Voter ID (5 digits)
- **Vote tracking** to prevent duplicate votes using the same ID
- **Real-time results** showing:
  - Current vote counts for each party
  - Leading party detection
  - Tie detection between parties
- **Responsive design** with clear error messages

## Technical Details

- Built with Java Swing for the graphical interface
- Uses `HashSet` to track voted IDs and prevent duplicates
- Implements input validation with regular expressions
- Features a clean, organized layout with proper alignment and spacing

## How to Use

1. Fill in your name, phone number, and unique 5-digit ID
2. Select your preferred party
3. Click "Submit Vote" to cast your vote
4. Click "Check Results" at any time to see current voting statistics

The application will prevent invalid submissions and notify you of any errors in the input data.
