# Simple Web Crawler

A simple web crawler that extracts and prints URLs from a given starting point.

## Author
**Himanshu Khadka**

## Version
1.0

## Overview
This program prompts the user to enter a URL, and then crawls up to 100 URLs starting from the given URL. It prints each URL it visits and extracts additional URLs from the HTML content of each visited page.

## Classes

### webcrawler

#### Methods

1. **main(String[] args)**:
    - Prompts the user to enter a URL.
    - Initiates the crawling process by calling the `crawl` method with the provided URL.

2. **crawl(String URLString)**:
    - Manages the crawling process.
    - Uses two `ArrayList` objects to keep track of pending and visited URLs.
    - Crawls up to 100 URLs, printing each one and extracting additional URLs using the `getSubString` method.

3. **getSubString(String URLstring)**:
    - Extracts and returns a list of URLs found in the HTML content of the given URL.
    - Handles `MalformedURLException` and `IOException`.

## Usage

1. **Input**: The user is prompted to enter a starting URL.
2. **Processing**:
    - The program adds the starting URL to a pending list and begins crawling.
    - For each URL, it prints the URL and extracts new URLs found in the page content.
3. **Output**: Prints the URLs being crawled and the URLs found on each page.

## Example
```bash
Enter the URL :
https://example.com
Crawl: https://example.com
https://example.com/page1
https://example.com/page2
...
