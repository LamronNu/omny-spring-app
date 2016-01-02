package com.dev.controller;

import com.dev.repository.UserRepository;
import com.dev.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author OlgaPrylypko
 *         date: 30.12.2015
 */

@Controller
public class WelcomeController {
    private static final Logger LOG = Logger.getLogger(WelcomeController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        LOG.info("welcome page");
        model.put("users", userRepository.findAll());
        return "welcome";
    }

    @ModelAttribute("batchJob")
    public BatchJob initBatchJob(Map<String, Object> model) {
        LOG.info("init batchJob");
        return new BatchJob();
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public void runBatch(@ModelAttribute("batchJob") BatchJob job, HttpServletResponse httpResponse) {
        LOG.info("run job!");
        Integer daysCount = job.daysCount;
        if (daysCount == null) {
            return;
        }
        File resultFile = userService.getCsvFile(daysCount);
        if (resultFile != null) {//write the result to response
            writeFileToResponse(httpResponse, resultFile);
        }
    }

    private void writeFileToResponse(HttpServletResponse httpResponse, File resultFile) {
        try {
            String fileName = resultFile.getName();
            httpResponse.setContentType("text/csv;charset=UTF-8");
            httpResponse.setHeader("Content-disposition", "attachment; filename=" + fileName);
            try (FileInputStream inputStream = new FileInputStream(fileName)) {
                int c;
                while ((c = inputStream.read()) != -1) {
                    httpResponse.getWriter().write(c);
                }
            } finally {
                httpResponse.getWriter().close();
                resultFile.delete();
            }

        } catch (IOException e) {
            LOG.error("cant write result file to response!", e);
        }
    }

    class BatchJob {
        Integer daysCount;

        public Integer getDaysCount() {
            return daysCount;
        }

        public void setDaysCount(Integer daysCount) {
            this.daysCount = daysCount;
        }
    }
}
