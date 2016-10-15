package org.cd.sport.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cd.sport.constant.Constants;

/**
 * 验证生成类
 * 
 * @author liuyk
 *
 */
public class VerifCode {

	private int width = 84;// 定义图片的宽度
	private int height = 29;// 定义图片的高度
	private int codeCount = 4;// 定义验证码的个数
	private int margin = 15;// 横向间隔
	private int fontHeight = 20;// 字体的高度
	private int codeY = 20;// 验证码高度
	private char[] codeSequence = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z' };
	
	public static final String KEY = Constants.Common.VERIF_CODE;

	public Map<String, Object> creat() {
		Map<String, Object> maps = new HashMap<String, Object>();
		// 定义图像bufferImage
		BufferedImage bufferImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 使背景为白色
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度决定。
		Font font = new Font("validateCode", Font.BOLD, fontHeight);
		g.setFont(font);
		// 画边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生30条干扰线，使图像的验证码不易被其他程序看清
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(10);
			int y1 = random.nextInt(10);
			g.drawLine(x, y, x + x1, y + y1);
		}
		// randomCode用于保存随机产生的验证码
		StringBuilder randomCode = new StringBuilder();
		int red = 0, green = 0, blue = 0;
		// 随机产生codeCount数字的验证码
		for (int i = 0; i < codeCount; i++) {
			String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 产生随机的颜色给产生的验证码涂色
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中
			g.setColor(new Color(red, green, blue));
			g.drawString(code, (1 + i) * margin, codeY);
			// 将产生的四个随机验证码组合在一起
			randomCode.append(code);
		}
		maps.put("code", randomCode);
		maps.put("img", bufferImg);
		return maps;
	}

	public void write(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> maps = this.creat();
		HttpSession session = request.getSession();
		// 将 生成的四个验证码保存在session中
		session.setAttribute(KEY, maps.get("code").toString());
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");

		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			ImageIO.write((BufferedImage) maps.get("img"), "jpeg", sos);
			sos.flush();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sos != null) {
				try {
					sos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
