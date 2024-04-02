// Footer.js

import React from 'react';
import { Box, Flex, Link, Text } from '@chakra-ui/react';

function Footer() {
  return (
    <Box as="footer" bg="gray.800" color="white" p="4">
      <Flex justify="space-between" align="center" flexWrap="wrap">
        <Box flex="1">
          <Text fontWeight="bold" fontSize="lg">Thông tin liên hệ</Text>
          <Text fontSize="sm">123 Street, City, Country</Text>
          <Text fontSize="sm">Email: example@example.com</Text>
          <Text fontSize="sm">Phone: 123-456-7890</Text>
        </Box>
        <Box flex="1" mt={{ base: '4', md: '0' }}>
          <Text fontWeight="bold" fontSize="lg">Liên kết hữu ích</Text>
          <Link display="block" mt="2" fontSize="sm" href="#">Trang chủ</Link>
          <Link display="block" mt="2" fontSize="sm" href="#">Sản phẩm</Link>
          <Link display="block" mt="2" fontSize="sm" href="#">Dịch vụ</Link>
          <Link display="block" mt="2" fontSize="sm" href="#">Giới thiệu</Link>
        </Box>
        <Box flex="1" mt={{ base: '4', md: '0' }}>
          <Text fontWeight="bold" fontSize="lg">Theo dõi chúng tôi</Text>
          <Link display="block" mt="2" fontSize="sm" href="#">Facebook</Link>
          <Link display="block" mt="2" fontSize="sm" href="#">Instagram</Link>
          <Link display="block" mt="2" fontSize="sm" href="#">Twitter</Link>
        </Box>
      </Flex>
      <Text mt="4" textAlign="center">© 2024 Your Company. All rights reserved.</Text>
    </Box>
  );
}

export default Footer;
